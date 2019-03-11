package cc.edt.frame.common.controller.requestbody;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * BodyReaderHttpServletRequestWrapper
 *
 * @author 刘钢
 * @date 2018/11/26 14:13
 */
public class RequestReaderHttpServletRequestWrapper
        extends HttpServletRequestWrapper {
    private final byte[] body;

    public RequestReaderHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        body = RequestBodyHelper.getBodyString(request)
                .getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() {

        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {

            @Override
            public int read() {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }
}
