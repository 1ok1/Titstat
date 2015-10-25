
package elitecorp.titstat.com.titstat_hackathon.newsfeedretro;

import android.content.ContentValues;

import java.io.Serializable;

public class MemeberTable implements Serializable {

    private String messageId;
    private String messageTableId;
    private String messageSubject;
    private String messageReceived;
    private String messageDisplayDate;
    private boolean messageRead;
    private String messageTitle;
    //	private String messageType;
    private String messageSensorImage;
    public static String MESSAGE_ID = "messageId";
    public static String MESSAGE_TABLE_ID = "messageTableId";
    public static String MESSAGE_SUBJECT = "messageSubject";
    public static String MESSAGE_RECEIVED = "messageReceived";
    public static String MESSAGE_DISPLAY_DATE = "messageDisplayDate";
    public static String MESSAGE_READ = "messageRead";
    public static String MESSAGE_TITLE = "messageTitle";
    public static String MESSAGE_TYPE = "messageType";
    public static String MESSAGE_SENSOR_IMAGE = "messageSensorImage";


    public static String[] messageAllFields = {MESSAGE_ID, MESSAGE_TABLE_ID, MESSAGE_RECEIVED, MESSAGE_SUBJECT, MESSAGE_TITLE, MESSAGE_READ, MESSAGE_SENSOR_IMAGE};


    public String getMessageSensorImage() {
        return messageSensorImage;
    }

    public void setMessageSensorImage(String messageSensorImage) {
        this.messageSensorImage = messageSensorImage;
    }

    //	public String getMessageType() {
//		return messageType;
//	}
//	public void setMessageType(String messageType) {
//		this.messageType = messageType;
//	}
    public String getMessageTableId() {
        return messageTableId;
    }

    public void setMessageTableId(String messageTableId) {
        this.messageTableId = messageTableId;
    }

    public boolean isMessageRead() {
        return messageRead;
    }

    public void setMessageRead(boolean messageRead) {
        this.messageRead = messageRead;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public String getMessageReceived() {
        return messageReceived;
    }

    public void setMessageReceived(String messageReceived) {
        this.messageReceived = messageReceived;
    }

    public String getMessageDisplayDate() {
        return messageDisplayDate;
    }

    public void setMessageDisplayDate(String messageDisplayDate) {
        this.messageDisplayDate = messageDisplayDate;
    }

    public ContentValues getDbContentValues(MemeberTable notificationMessage) {
        ContentValues args = new ContentValues();
        args.put(MESSAGE_ID, "" + notificationMessage.getMessageId());
        args.put(MESSAGE_RECEIVED, "" + notificationMessage.getMessageReceived());
        args.put(MESSAGE_SUBJECT, "" + notificationMessage.getMessageSubject());
        args.put(MESSAGE_TITLE, "" + notificationMessage.getMessageTitle());
        args.put(MESSAGE_READ, notificationMessage.isMessageRead());
        args.put(MESSAGE_SENSOR_IMAGE, "" + notificationMessage.getMessageSensorImage());
        return args;
    }
}