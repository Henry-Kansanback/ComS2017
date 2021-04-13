package hw1;

public class Television {
	/***
	 * @author HenryKansanback
	 * @param channel The current channel
	 * @param volume The current volume
	 * @param channelMax The current maximum channel number
	 * @param previousChannel Tracks the previous channel viewed
	 */
	private int channel;
	private double volume;
	private int channelMax;
	private int previousChannel;
	public static final double VOLUME_INCREMENT = 0.07;
	public Television(int givenChannelMax)
	{
		this.channelMax = (givenChannelMax-1);
		channel = 0;
		this.volume = 0.5;
	}
	/**
	 * @param channelDown() Decreases the channel by 1
	 */
	public void channelDown()
	{
		this.previousChannel = this.channel;
		this.channel = ((this.channel-1)+(this.channelMax+1)) % (this.channelMax+1);
	}
	/**
	 * @param channelUp() Increases the channel by 1
	 */
	public void channelUp()
	{
		this.previousChannel = this.channel;
		this.channel = ((this.channel+1) % (this.channelMax+1));
	}
	public String display()
	{
		int x = this.channel;
		int y = (int) Math.round(volume*100);
		String display = ("Channel "+x+" Volume "+y+"%");
		return display;
	}
	
	public double getVolume()
	{
		return this.volume;
	}
	public void goToPreviousChannel()
	{
		if(this.previousChannel>this.channelMax)
		{
			this.channel = this.channelMax;
		}
		else
		{
		this.channel = this.previousChannel;
		}
	}
	public void resetChannelMax(int givenMax)
	{
		this.channelMax = (givenMax-1);
		if(this.channel>this.channelMax)
		{
			this.channel = this.channelMax;
		}
		if(this.previousChannel>this.channelMax)
		{
			this.previousChannel = this.channelMax;
		}		
		//this.channel = this.channelMax;
	}
	public void setChannel(int channelNumber)
	{
		int newChannel = channelNumber;
		if(this.channelMax < channelNumber)
		{
			this.previousChannel = this.channel;
			newChannel = Math.min(this.channelMax, channelNumber);
			this.channel = newChannel;
		}
		if(channelNumber < 0)
		{
			this.previousChannel = this.channel;
			newChannel = Math.max(0, channelNumber);
			this.channel = newChannel;
		}
		else
		{			
		this.previousChannel = this.channel;
		this.channel = newChannel;		
		}

	}
	public int getChannel()
	{
		return this.channel;
	}
	/***
	 * @param volumeDown() decreases volume by 0.07
	 * also checks if volume is below 0.0
	 */
	public void volumeDown()
	{
		this.volume =  (Math.max(0.0, (this.volume - Television.VOLUME_INCREMENT)/1.0));
	}
	/***
	 * @param volumeUp() increases volume by 0.07
	 * also checks if volume exceeds 1.0
	 */
	public void volumeUp()
	{
		this.volume =  (Math.min(1.0, (this.volume + Television.VOLUME_INCREMENT)/1.0));
	}

}
