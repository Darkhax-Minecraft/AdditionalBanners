package net.epoxide.additionalbanners.lib;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constants {
    
    public static final String MOD_ID = "additionalbanners";
    public static final String MOD_NAME = "Additional Banners";
    public static final String VERSION_NUMBER = "1.0.0";
    public static final String MCVERSION = "[1.8,1.8.9]";
    public static final String CLIENT_PROXY_CLASS = "net.epoxide.additionalbanners.client.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "net.epoxide.additionalbanners.common.CommonProxy";
    public static final Random RANDOM = new Random();
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);
}