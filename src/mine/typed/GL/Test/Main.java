package mine.typed.GL.Test;

import mine.typed.core.lua.LuaFTest;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;


public class Main{

	public static void main(String[] arg){
		
		Globals globals = JsePlatform.standardGlobals();
		LuaValue chunk = globals.loadString("print 'TypeLua Test <Main>'", "hi");
		chunk.call();
		
		new LuaFTest().call();
		//파일을 불러올때 프로젝트의 가장 최상단에서 부터 시작하는 것 같다.
		LuaValue loadFile = globals.loadFile("mode/lua/hello.lua");
		loadFile.call();

		System.out.println();
		
	}

}
