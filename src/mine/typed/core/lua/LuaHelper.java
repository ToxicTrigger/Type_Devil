package mine.typed.core.lua;

import org.luaj.vm2.Globals;

public class LuaHelper {
	private static boolean isLuaUsable;
	private static boolean isLuaModeUsable;
	
	public static boolean hasLuaUsable() {
		return isLuaUsable;
	}
	public static void setLuaUsable(boolean isLuaUsable) {
		LuaHelper.isLuaUsable = isLuaUsable;
	}
	public static boolean hasLuaModeUsable() {
		return isLuaModeUsable;
	}
	public static void setLuaModeUsable(boolean isLuaModeUsable) {
		LuaHelper.isLuaModeUsable = isLuaModeUsable;
	}
	
	private static Globals mG;
	
	public static Globals getGlobal(){
		mG = new Globals();
		return mG;
	}
}
