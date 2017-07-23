package agent;

import common.model.Message;

/**
 * Created by phiha on 22/07/2017.
 */
public interface Agent {

    public void receive(Message message);

    public void send();
}
