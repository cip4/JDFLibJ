/**
 * The CIP4 Software License, Version 1.0
 *
 * Copyright (c) 2001-2021 The International Cooperation for the Integration of 
 * Processes in  Prepress, Press and Postpress (CIP4).  All rights 
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:  
 *       "This product includes software developed by the
 *        The International Cooperation for the Integration of 
 *        Processes in  Prepress, Press and Postpress (www.cip4.org)"
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "CIP4" and "The International Cooperation for the Integration of 
 *    Processes in  Prepress, Press and Postpress" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written 
 *    permission, please contact info@cip4.org.
 *
 * 5. Products derived from this software may not be called "CIP4",
 *    nor may "CIP4" appear in their name, without prior written
 *    permission of the CIP4 organization
 *
 * Usage of this software in commercial products is subject to restrictions. For
 * details please consult info@cip4.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE INTERNATIONAL COOPERATION FOR
 * THE INTEGRATION OF PROCESSES IN PREPRESS, PRESS AND POSTPRESS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the The International Cooperation for the Integration 
 * of Processes in Prepress, Press and Postpress and was
 * originally based on software 
 * copyright (c) 1999-2001, Heidelberger Druckmaschinen AG 
 * copyright (c) 1999-2001, Agfa-Gevaert N.V. 
 *  
 * For more information on The International Cooperation for the 
 * Integration of Processes in  Prepress, Press and Postpress , please see
 * <http://www.cip4.org/>.
 *  
 * 
 */
package org.cip4.jdflib.util.logging;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.AppenderRefComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.RootLoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.cip4.jdflib.util.UrlUtil;

/**
 * @author rainer prosi
 * @date May 18, 2011
 */
public class LogConfigurator
{

	public static void main(String[] args)
	{
		final Logger logger = LogManager.getLogger(LogConfigurator.class);

		configureLog("c:/a", "main.log");
		Log log = LogFactory.getLog(LogConfigurator.class);
		log.info("pre");
		logger.info("pre2");
		for (int i = 0; i < 5; i++)
			log.info("Configured logging ");

	}

	/**
	 * @param logDir the log directory, null console only
	 * @param logName the name of the log file
	 */
	public static void configureLog(String logDir, final String logName)
	{
		Log log = LogFactory.getLog(LogConfigurator.class);
		try
		{
			ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();
			builder.setConfigurationName("DefaultLogger");

			LayoutComponentBuilder newLayout = builder.newLayout(PatternLayout.class.getSimpleName());
			newLayout.addAttribute("pattern", "%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n");

			// configure a console appender
			AppenderComponentBuilder consoleAppender = builder.newAppender("stdout", "Console");
			consoleAppender.add(newLayout);
			builder.add(consoleAppender);

			// configure the root logger
			RootLoggerComponentBuilder newRootLogger = builder.newRootLogger(Level.INFO);
			AppenderRefComponentBuilder newAppenderRef = builder.newAppenderRef("stdout");
			newRootLogger.add(newAppenderRef);

			if (logDir != null)
			{
				new File(logDir).mkdirs();
				String logFileName = logDir + "/" + logName;
				if (UrlUtil.extension(logFileName) == null)
					logFileName += ".log";

				// configure a console appender
				AppenderComponentBuilder fileleAppender = builder.newAppender("file", "RollingFile");
				fileleAppender.add(newLayout);
				fileleAppender.addAttribute("fileName", logFileName);
				String pattern = UrlUtil.newExtension(logFileName, "%i." + UrlUtil.extension(logFileName));
				fileleAppender.addAttribute("filePattern", pattern);

				ComponentBuilder<?> policies = builder.newComponent("Policies");
				ComponentBuilder<?> trigger = builder.newComponent("SizeBasedTriggeringPolicy");
				trigger.addAttribute("size", "10M");
				policies.addComponent(trigger);
				fileleAppender.addComponent(policies);

				ComponentBuilder<?> roll = builder.newComponent("DefaultRolloverStrategy");
				roll.addAttribute("max", 10);
				fileleAppender.addComponent(roll);

				builder.add(fileleAppender);

				AppenderRefComponentBuilder fileAppenderRef = builder.newAppenderRef("file");
				newRootLogger.add(fileAppenderRef);

			}
			builder.add(newRootLogger);
			BuiltConfiguration c = builder.build();
			Configurator.reconfigure(c);
			log.info("Configured logging " + logDir);
		}
		catch (Exception e)
		{
			log.error("Snafu while configuring logging ", e);
		}
	}
}
