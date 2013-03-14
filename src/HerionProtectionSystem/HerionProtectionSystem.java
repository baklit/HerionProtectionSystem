package HerionProtectionSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Scanner;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid="HerionProtectionSystem", name="HerionProtectionSystem", version="1.0.0")
@NetworkMod(clientSideRequired=false, serverSideRequired=false)

public class HerionProtectionSystem{
	
	private File datafolder = new File("config" + File.separator + "Herion Protection System");
	File config = new File(getDataFolder().getAbsolutePath());
	File PortalBlockListFile = new File(getDataFolder() + File.separator + "PortalBlockListFile.txt");
	
	public static HashMap<Number,Number> PortalBlockListHashMap = new HashMap<Number,Number>();


        @Instance("HerionProtectionSystem")
        public static HerionProtectionSystem instance;
              
        @PreInit
        public void preInit(FMLPreInitializationEvent event) {
        	
        	System.out.println("HerionProtectionSystem Loading!");
        	DisablePortalGunBlocks();
        	
                
        }
        
        public void DisablePortalGunBlocks(){
        	System.out.println("Disabling Portalgun grabbing blocks...");
        	createPluginFolder();
        	createPortalBlockListFile();
        	createPortalBlockListHashMap();
        	System.out.println("Disabling done!");
        	
        }
        
        public void createPluginFolder() {
    		if (!getDataFolder().exists()) {
    			getDataFolder().mkdir();
    		}
    	}
        
    	public void createPortalBlockListFile() {
    		if (!this.PortalBlockListFile.exists()) {
    			try {
    				PortalBlockListFile.createNewFile();
    			} catch (IOException e) {
    				System.out.println("!!!!!!!HERION PROTECTION SYSTEM REPORT ERROR CODE: 2 TO BAKLIT!!!!!!");
    				e.printStackTrace();
    			}
    		}
    		}
    	
    	public void createPortalBlockListHashMap(){
    		Scanner s = null;
			try {
				s = new Scanner(PortalBlockListFile);
			} catch (FileNotFoundException e) {
				System.out.println("!!!!!!!HERION PROTECTION SYSTEM REPORT ERROR CODE: 3 TO BAKLIT!!!!!!");
				e.printStackTrace();
			}
    		while (s.hasNextLine()){
    			String line = s.nextLine();
    			PortalBlockListHashMap.put(PortalBlockListHashMap.size()+1, Integer.parseInt(line));
    			System.out.println("[HerionProtectionSystem]Disabling id:" + Integer.parseInt(line));
    			int[] NoMetaData = new int[]{-1};
            	addBlockIDToGrabList(Integer.parseInt(line), NoMetaData);
    		}
    		s.close();
    		
    	}
    	
    	public File getDataFolder() {
    		return datafolder;
    	}


        
        public static void addBlockIDToGrabList(int blockID, int[] metadata) {
        	try {
        		Class.forName("portalgun.common.PortalGun").getDeclaredMethod("addBlockIDToGrabList", int.class, int[].class).invoke(null, blockID, metadata);
        	} catch (Exception e) {
        		System.out.println("!!!!!!!HERION PROTECTION SYSTEM REPORT ERROR CODE: 1 TO BAKLIT!!!!!!");
        		throw new RuntimeException(e);
        	}
        }
        
        @Init
        public void load(FMLInitializationEvent event) {

        }
        
        @PostInit
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }
        
        @ServerStarting
        public void serverStarting(FMLServerStartingEvent event){
        	MinecraftForge.EVENT_BUS.register(new EventBlockInteractContainerClass());
        	
        }

}