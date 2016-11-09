package filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class ChineseModifiedFilter
 */
@WebFilter(urlPatterns={"/Login","/MyExerciseList","/LoginIncomplete","/LoginPasswordError","/LoginUserNotExsit"}, 
filterName= "ResponseChineseFilter", initParams={@WebInitParam( name  ="characterEncoding", value="GBK")})
public class ResponseChineseFilter implements Filter {
	
	private String characterEncoding;

    /**
     * Default constructor. 
     */
    public ResponseChineseFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		//��ʼ������
		characterEncoding=fConfig.getInitParameter("characterEncoding");
		
		if(characterEncoding==null){
			throw new ServletException("ResponseChineseFilter named " + fConfig.getFilterName() +" missing characterEncoding init parameter.");
		}
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		//��Ӧ�е�����������
		BufferedResponse resWrapper=new BufferedResponse((HttpServletResponse)response);
		//�����������Ӧ
		resWrapper.setCharacterEncoding("GBK");
		chain.doFilter(request, resWrapper); 
		
		if(resWrapper.getContentType().equals("text/html")){	
			String resBody = new String(resWrapper.toByteArray(),resWrapper.getCharacterEncoding());	
			
			//response.setCharacterEncoding("GBK");
			
			PrintWriter writer = response.getWriter();
			writer.println(resBody);//������д��ʵ����Ӧ������
		}else{
			throw new ServletException("ResponseChineseFilter get error contentType "+resWrapper.getContentType());
		}
	}

}
