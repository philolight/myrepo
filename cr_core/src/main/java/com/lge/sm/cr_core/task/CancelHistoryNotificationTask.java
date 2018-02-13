package com.lge.sm.cr_core.task;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.lge.framework.ceasar.event.EventBroker;
import com.lge.framework.ceasar.event.event_kind.CreateEvent;
import com.lge.framework.ceasar.event.subscriber.EventSubscriber;
import com.lge.framework.ceasar.util.CsvUtil;
import com.lge.framework.ceasar.util.DateUtil;
import com.lge.framework.ceasar.util.FileUtil;
import com.lge.framework.ceasar.util.ToString;
import com.lge.framework.pacific.logger.Logger;
import com.lge.sm.cr_core.common.Constants;
import com.lge.sm.cr_core.property_manager.PropertyManager;
import com.lge.sm.cr_data_store.entity.AuthorityEntity;
import com.lge.sm.cr_data_store.entity.AuthorityLocationEntity;
import com.lge.sm.cr_data_store.entity.CancelHistoryEntity;
import com.lge.sm.cr_data_store.entity.LocationEntity;
import com.lge.sm.cr_data_store.entity.PartyAuthorityEntity;
import com.lge.sm.cr_data_store.entity.PartyEntity;
import com.lge.sm.cr_data_store.entity.PartyUserEntity;
import com.lge.sm.cr_data_store.entity.PersonEntity;
import com.lge.sm.cr_data_store.entity.UserAuthorityEntity;
import com.lge.sm.cr_data_store.entity.UserEntity;

public class CancelHistoryNotificationTask extends Task{
	private static final String TAG = CancelHistoryNotificationTask.class.getSimpleName();
	
	private LinkedBlockingQueue<CancelHistoryEntity> q = new LinkedBlockingQueue<>(100);
	
	private EventSubscriber<CreateEvent<CancelHistoryEntity>> createCancelHistoryEventSubscriber = new EventSubscriber<CreateEvent<CancelHistoryEntity>>() {
		@Override
		public void subscribe(CreateEvent<CancelHistoryEntity> event) {
			try {
				q.put(event.getTarget());
			} catch (InterruptedException e) {
				Logger.error(TAG, "Failed to put CancelHistoryEntity into Queue");
				return;
			}
			run();
		}
	};
	
	@Override
	public void init() {
		EventBroker.subscribe(CancelHistoryEntity.class, CreateEvent.class, createCancelHistoryEventSubscriber);
	}

	@Override
	public String getWatchableId() {
		return TAG;
	}

	@Override
	public String getModifiedTime() {
		return "20171222000000";
	}
		
	@Override
	protected void runTask() {
		CancelHistoryEntity cancelHistory = q.remove();
			
		if(makeDailyCancelHistoryFile(cancelHistory) == false) return; // alread handled exception
		
		sendReportEmail(cancelHistory);
	}
	
	public Set<String> makeEmailList(LocationEntity location){
		List<AuthorityLocationEntity> authorityLocationList = location.getAuthorityLocationEntityList();
		List<AuthorityEntity> authorityList = new ArrayList<>();
		for(AuthorityLocationEntity each : authorityLocationList) {
			AuthorityEntity authorityEntity = each.getAuthorityEntity();
			if(authorityEntity == null) {
				Logger.error(TAG, "Cannot find AuthorityEntity for : " + ToString.toLine(each));
				return null;
			}
			
			authorityList.add(authorityEntity);
		}
		
		List<UserEntity> usersToReceiveNotification = new ArrayList<>();		
	
		for(AuthorityEntity each : authorityList) {
			List<PartyAuthorityEntity> partyAuthorityList = each.getPartyAuthorityEntityList();				
			for(PartyAuthorityEntity partyAuthorityEntity : partyAuthorityList) {
				PartyEntity partyEntity = partyAuthorityEntity.getPartyEntity();
				if(partyEntity == null) {
					Logger.error(TAG, "Cannot find PartyEntity for : " + ToString.toLine(partyAuthorityEntity));
					return null;
				}
				List<PartyUserEntity> partyUserList = partyEntity.getPartyUserEntityList();
				for(PartyUserEntity partyUserEntity : partyUserList) {
					UserEntity userEntity = partyUserEntity.getUserEntity();
					if(userEntity != null) usersToReceiveNotification.add(userEntity);
				}
			}
			
			List<UserAuthorityEntity> userAuthorityList = each.getUserAuthorityEntityList();
			for(UserAuthorityEntity userAuthorityEntity : userAuthorityList) {
				UserEntity userEntity = userAuthorityEntity.getUserEntity();
				if(userEntity != null) {
					usersToReceiveNotification.add(userEntity);
				}
			}
		}
		
		Set<String> emailList = new HashSet<String>();
		
		for(UserEntity each : usersToReceiveNotification) {
			List<PersonEntity> personEntityList = each.getPersonEntityList();
			for(PersonEntity person : personEntityList) {
				emailList.add(person.getEmail());
			}
		}
		
		return emailList;
	}
	
	protected boolean makeDailyCancelHistoryFile(CancelHistoryEntity cancelHistory) {
		LocationEntity location = cancelHistory.getLocationEntity();
		if(location == null) {
			Logger.error(TAG, "Cannot find LocationEntity for : " + ToString.toLine(cancelHistory));
			return false;
		}
		
		String folder = "./" + PropertyManager.getPropertyManager().get(Constants.CR_CORE_CONFIG_FILE_NAME, "cr.daily-report.folder", null);
		String extension = PropertyManager.getPropertyManager().get(Constants.CR_CORE_CONFIG_FILE_NAME, "cr.daily-report.extension", null);
		String fileName = "daily_" + location.getLocationId() + "." + extension;
		
		boolean fileCreated = FileUtil.createFileIfNotExist(folder, fileName);
		String fullPathFileName = folder + "/" + fileName;
		BufferedWriter fw = FileUtil.getCsvFileBufferedWriter(fullPathFileName);
		
		if(fw == null) {
			Logger.error(TAG, "Cannot open File : " + fullPathFileName);
			return false;
		}

		try {
			if(fileCreated == true) { // CSV 파일이 새로 만들어졌다면 컬럼 이름을 추가한다.
				fw.write(
						"Date" + "," +
						"Reservations" +"," +
						"Cancels" +"," +
						"CancelRate(%)" + "," +
						"Reuses" + "," +
						"CancelMinutes" + "\r\n"
						);								
			}

			fw.write(
					CsvUtil.asColumn(cancelHistory.getDateOf()) + "," +
					cancelHistory.getReservations() + "," +
					cancelHistory.getCancels() + "," +
					cancelHistory.getCancelRate() + "," +
					cancelHistory.getReuses() + "," +
					cancelHistory.getCancelMinutes() + "\r\n"
					);
			
			fw.flush();
			fw.close();
		} catch (IOException e) {
			Logger.error(TAG, "Cannot write File : " + fullPathFileName);
			return false;
		}
		
		return true;
	}
	
	protected void sendReportEmail(CancelHistoryEntity cancelHistory) {
		LocationEntity location = cancelHistory.getLocationEntity();
		if(location == null) {
			Logger.error(TAG, "Cannot find LocationEntity for : " + ToString.toLine(cancelHistory));
			return;
		}
		
		Set<String> emailList = makeEmailList(location);
		if(emailList == null) return; // alread handled exception
		
		Properties p = System.getProperties();
		
		String folder = PropertyManager.getPropertyManager().get(Constants.CR_CORE_CONFIG_FILE_NAME, "cr.daily-report.folder", null);
		String extension = PropertyManager.getPropertyManager().get(Constants.CR_CORE_CONFIG_FILE_NAME, "cr.daily-report.extension", null);		
		String cancelListFileName = "./" + folder + "/" + location.getLocationId() + "." + extension;
		String cancelDailyReportFileName = "./" + folder + "/daily_" + location.getLocationId() + "." + extension;
		
		String username = PropertyManager.getPropertyManager().get(Constants.CR_CORE_CONFIG_FILE_NAME, "cr.daily-report.mail.smtp.username", null);		
		String password = PropertyManager.getPropertyManager().get(Constants.CR_CORE_CONFIG_FILE_NAME, "cr.daily-report.mail.smtp.password", null);		
		String smtpHost = PropertyManager.getPropertyManager().get(Constants.CR_CORE_CONFIG_FILE_NAME, "cr.daily-report.mail.smtp.host", null);
	
		p.setProperty("mail.smtp.host", smtpHost); // 회사 SMTP 서버 주소
		
		// 위 환경 정보로 "메일 세션"을 만들고 빈 메시지를 만든다	
		Session session = Session.getInstance(p,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("sy3.lee@lge.com"));
			for(String email : emailList) message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

			Date date = DateUtil.stringToDate(cancelHistory.getDateOf(), Constants.DEFAULT_DATE_FORMAT);
			
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			String dateString = c.get(Calendar.YEAR) + "년 " + (c.get(Calendar.MONTH)+1) + "월 " + c.get(Calendar.DAY_OF_MONTH) + "일자";
			message.setSubject(dateString + " 회의실 예약 취소 목록", "utf-8");
			message.setText("Dear Mail Crawler," + "\n\n No spam to my email, please!");
			
			MimeMultipart multipart = new MimeMultipart();
			
		    MimeBodyPart part = new MimeBodyPart();
		    part.setContent("회의 취소 시스템에서 알려 드립니다.\r\n" +
		    		dateString + 
		    		" 회의목록과 일일 리포트를 보내 드립니다.\r\n\r\n\r\n" +
		    		"감사합니다.\r\n"
		    		, "text/html; charset=utf-8");
		    multipart.addBodyPart(part);
		     
		    part = new MimeBodyPart();
		    FileDataSource clfds = new FileDataSource(cancelListFileName);
		    part.setDataHandler(new DataHandler(clfds));
		    part.setFileName(cancelListFileName.substring(cancelListFileName.lastIndexOf('/') + 1));
		    multipart.addBodyPart(part);
		    
		    part = new MimeBodyPart();
		    FileDataSource cdfds = new FileDataSource(cancelDailyReportFileName);
		    part.setDataHandler(new DataHandler(cdfds));
		    part.setFileName(cancelDailyReportFileName.substring(cancelDailyReportFileName.lastIndexOf('/') + 1));
		    multipart.addBodyPart(part);
		     
		    message.setContent(multipart);
		    
			Transport.send(message);
			Logger.info(TAG, "Cancel reservation report notification email sended for location : " + location);
			
			StringBuffer emailListBuffer = new StringBuffer();
			
			for(String each : emailList) emailListBuffer.append(each + ",");
			Logger.info(TAG, "to : " + emailListBuffer.toString());
		} catch (MessagingException e) {
			Logger.error(TAG, "Failed to send Cancel Daily Report Email");
		}
	}
}
