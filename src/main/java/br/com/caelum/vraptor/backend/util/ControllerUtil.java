package br.com.caelum.vraptor.backend.util;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Result;

/**
 * @author Fidelis
 *
 */
public class ControllerUtil {
	private ControllerUtil() {
	}
	
	/**
	 * Recebe a classe controller e retorna seu contexto padrão lowerCase
	 * @param classeController
	 * @return
	 */
	public static String getContexto(Class<? extends Object> classeController) {
		return classeController.getSimpleName().substring(0,classeController.getSimpleName().length()-10).toLowerCase();
	}

	public static void addContextAndPath(Result result, Class<? extends Object> class1, HttpServletRequest req) {
		result.include("path", req.getContextPath());
		result.include("contexto", getContexto(class1));
	}
	
}