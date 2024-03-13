package com.trkj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    private  static Logger logger = LogManager.getLogger(App.class);

    public static void main( String[] args )
    {
            logger.trace("跟踪...{}","aaa");
            logger.debug("调试...");
            logger.info("信息...");
            logger.warn("警告...");
            logger.error("出错了....");
            logger.fatal("系统崩了...");

    }
}
