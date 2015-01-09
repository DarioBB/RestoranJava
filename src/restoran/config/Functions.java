package restoran.config;

import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Functions {

	public String char_to_utf8(String fieldvalue) throws UnsupportedEncodingException{
		
		String fieldvalue_utf8 = new String(fieldvalue.getBytes("ISO-8859-1"), "UTF-8");
		return fieldvalue_utf8;
	}
}
