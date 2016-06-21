package br.com.caelum.vraptor.backend.util;

import java.util.List;

import org.jboss.weld.util.Preconditions;
import org.slf4j.Logger;
import org.slf4j.impl.Log4jLoggerFactory;


/**
 * @author fidelis.guimaraes
 *
 */
public class PreconditionUtil {
	private static Logger logger = new Log4jLoggerFactory().getLogger("br.com caelum.vraptor.sysweb");
	private static final String MSG_NULO = "O elemento não pode ser nulo";
	private static final String MSG_EMPTY = "O elemento não pode ser vazio";

	private PreconditionUtil() {
	}
	
	/**
	 * Is Not Null
	 * @param object
	 * @return
	 */
	public static boolean isNotNull(final Object object){
		try{
			Preconditions.checkArgumentNotNull(object, MSG_NULO);
		}catch(IllegalArgumentException e){
			logger.info(e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * Is Not Null
	 * @param object
	 * @return
	 */
	public static void isNotNullDoThrowsIllegalArgumentException(Object object){
		Preconditions.checkArgumentNotNull(object, MSG_NULO);
	}

	/**
	 * Is Not Empty
	 * @param perfis
	 */
	public static void isNotEmptyDoThrowsIllegalArgumentException(
			List<?> objects) {
		Preconditions.checkArgumentNotNull(objects, MSG_NULO);
		if(objects.isEmpty()){
			throw new IllegalArgumentException(MSG_EMPTY);
		}
	}
	
	
}
