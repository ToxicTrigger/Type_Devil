--LuaFTest 에 mine/typed/core/lua/LuaFTest 객체를 대입함
-- 해당 LuaFTest 는 따로 자바에서 구현된 클래스 임.
local LuaFTest = require ( 'mine/typed/core/lua/LuaFTest' )

--getNum is already in java 
--return data is ID for getNums Method
print( 'LuaFTest.getNum' , LuaFTest.getNum )

--java method run example
print( 'getNum()' , LuaFTest.getNum(41) )

