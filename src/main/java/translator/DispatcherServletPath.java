import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DispatcherServletPath implements org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath {

    @Value("${server.servlet.context-path}")
    private String servletContextPath;

    
    @Override
    public String getPath() {
        return servletContextPath;
    }

}