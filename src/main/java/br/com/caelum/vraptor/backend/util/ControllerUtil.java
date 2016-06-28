package br.com.caelum.vraptor.backend.util;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;

import javax.enterprise.context.ApplicationScoped;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.StringUtils;

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
	
	/**
	 * You can override this method for use a different convention for your
	 * controller name, given a type
	 */
	public static String extractControllerNameFrom(Class<?> type) {
		String prefix = extractPrefix(type);
		if (isNullOrEmpty(prefix)) {
			String baseName = StringUtils.lowercaseFirst(type.getSimpleName());
			if (baseName.endsWith("Controller")) {
				return "/" + baseName.substring(0, baseName.lastIndexOf("Controller"));
			}
			return "/" + baseName;
		} else {
			return prefix;
		}
	}
	
	protected static String extractPrefix(Class<?> type) {
		if (type.isAnnotationPresent(Path.class)) {
			String[] uris = type.getAnnotation(Path.class).value();
			checkArgument(uris.length == 1, "You must specify exactly one path on @Path at %s", type);
			return fixLeadingSlash(uris[0]);
		} else {
			return "";
		}
	}
	
	private static String fixLeadingSlash(String uri) {
		if (!uri.startsWith("/")) {
			return  "/" + uri;
		}
		return uri;
	}
	
}