package net.jnet.network;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

/**
 * Configures the pipeline.
 * @author Thomas Nappo
 */
public class PipelineFactory implements ChannelPipelineFactory {

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline next = Channels.pipeline();
		
		// setup frames
		next.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
		
		// setup codec
		next.addLast("encoder", new StringEncoder());
		next.addLast("decoder", new StringDecoder());
		
		// foward handler
		next.addLast("handler", new ChannelHandler());
		
		return next;
	}

}