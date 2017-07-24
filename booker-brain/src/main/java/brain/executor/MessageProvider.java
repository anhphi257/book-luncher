package brain.executor;

import common.model.Conversation;
import common.model.Message;
import common.model.User;
import brain.intent.model.UserIntent;

public class MessageProvider {
    public static Message getMessage(UserIntent intent, Conversation conversation) {

        switch (intent) {
            case AGREE:
                return doAgree(conversation);
            case DISAGREE:
                return doDisagree(conversation);
            case GREETING:
                return doGreeting(conversation);
            case REGISTER:
                return doRegister(conversation);
            case CANCEL:
                return doCancel(conversation);
            case CHECK:
                return doCheck(conversation);
            case OTHER:
                return doOther(conversation);
            default:
        }
        return null;
    }

    //TODO: implement your code here
    private static Message doDisagree(Conversation conversation) {
        return null;
    }

    private static Message doAgree(Conversation conversation) {
        return null;
    }

    private static Message doOther(Conversation conversation) {
        return null;
    }

    private static Message doCheck(Conversation conversation) {
        Message message = new Message();
        String fake_date = "01/08/2017";
        String content = String.format("");
        return null;
    }

    private static Message doCancel(Conversation conversation) {
        return null;
    }

    //Make message to ask user confirm registation
    private static Message doRegister(Conversation conversation) {
        Message message = new Message();
        String fake_date = "01/08/2017";
        message.setContent(String.format("Xác nhận đ/c %s đặt cơm hôm nay, ngày %s? (Có/không)",
                conversation.getUser().getName(), fake_date));
        message.setUser(User.rootUser());
        message.setTime(System.currentTimeMillis());
        return message;
    }

    //Make message to ask user
    private static Message doGreeting(Conversation conversation) {
        return null;
    }
}
