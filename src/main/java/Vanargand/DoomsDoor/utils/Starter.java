package Vanargand.DoomsDoor.utils;

import Vanargand.DoomsDoor.ramCleaner.RamCleaner;
import Vanargand.DoomsDoor.server.SslServer;
import Vanargand.DoomsDoor.server.StandardServer;
import Vanargand.DoomsDoor.server.ThreadPool;


public class Starter 
{
    public static void start(Loader loader) throws Exception
    {
    	Config.configure();
    	int maxThread = Integer.parseInt(Config.getInstance().get("MaxThread"));
    	if (maxThread <= 0) throw new Exception("Config error : MaxThread");
    	ThreadPool.init(maxThread);
    	
    	loader.load();
    	
    	if (Boolean.parseBoolean(Config.getInstance().get("enableHttp"))) {
    		StandardServer Standardserver = new StandardServer(
    				Integer.parseInt(Config.getInstance().get("HttpPort")));
    		ThreadPool.getInstance().execute(Standardserver);
    	}
    	if (Boolean.parseBoolean(Config.getInstance().get("enableHttps"))) {
    		SslServer.loadKeystore(Config.getInstance().get("KeyStore"), Config.getInstance().get("StorePassword"));
    		SslServer sslServer = new SslServer(Integer.parseInt(Config.getInstance().get("HttpsPort")));
    		ThreadPool.getInstance().execute(sslServer);
    	}
    	RamCleaner.getInstance().run();
    }
}
