package mine.typed.core.lua;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

public class LuaFTest extends OneArgFunction {

	@Override
	public LuaValue call(LuaValue arg) {
		LuaValue lib = tableOf();
		lib.set("getNum", new getNum());
		//ENV.set("LuaFTest", lib);
		return lib;
	}
	
	static class getNum extends OneArgFunction {
		@Override
		public LuaValue call(LuaValue arg) {
			return LuaValue.valueOf(String.valueOf(arg.checkint()));
		}
		
	}

}
