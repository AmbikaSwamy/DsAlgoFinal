package Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerLoad 
{
	private static Logger logger = LogManager.getLogger();
		
	public static void WarningMsg(String sMsg)
	{
		logger.warn(sMsg);
	}
	
	public static void ErrorMsg(String sMsg)
	{
		logger.error(sMsg);
	}
	
	public static void FatalErrMsg(String sMsg)
	{
		logger.fatal(sMsg);
	}
	
	public static void DebugMsg(String sMsg)
	{
		logger.debug(sMsg);
	}
	
	public static void SuccessMsg(String sMsg)
	{
		logger.info(sMsg);
	}
}
