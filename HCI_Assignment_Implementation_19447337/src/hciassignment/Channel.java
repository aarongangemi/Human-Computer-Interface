
package hciassignment;

/***********************************************
 * Purpose: This is the channel server used to create the list of channels
 * Date Modified: 29/04/2020
 * @author Aaron Gangemi
 */
public class Channel {
    private String ChannelName;
    //Stores the channel name
    public Channel(String name)
    {
        ChannelName = name;
    }
    //Getters and Setters for channel Name below
    public String getChannelName() {
        return ChannelName;
    }

    public void setChannelName(String ChannelName) {
        this.ChannelName = ChannelName;
    }

    
}
