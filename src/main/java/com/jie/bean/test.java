package com.jie.bean;


/*public class test {
    protected void buildResponse(BaseRequest request,
                                 BaseResponse response,
                                 byte[] bytes)  {
        switch (request.returnType()) {
            case URL:
                upload(bytes, response);
                break;
            case BASE64:
                base64(bytes, response);
                break;
            case STREAM:
                stream(bytes, request);
        }
    }


    private void upload(byte[] bytes, BaseResponse response)  {
        try {
            // 上传到图片服务器，根据各自的实际情况进行替换
            String path = UploadUtil.upload(bytes);

            if (StringUtils.isBlank(path)) { // 上传失败
                throw new InternalError(null);
            }

            response.setPath(path);
            response.setUrl(CdnUtil.img(path));
        } catch (IOException e) { // cdn异常
            log.error("upload to cdn error! e:{}", e);
            throw new CDNUploadError(e.getMessage());
        }
    }

    // 返回base64
    private void base64(byte[] bytes, BaseResponse response) {
        String base = Base64.getEncoder().encodeToString(bytes);
        response.setBase(base);
    }

    // 返回二进制图片
    private void stream(byte[] bytes, BaseRequest request) throws SelfError {
        try {
            MediaTypeEnum mediaType = request.mediaType();
            HttpServletResponse servletResponse = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            servletResponse.setContentType(mediaType.getMime());
            OutputStream os = servletResponse.getOutputStream();
            os.write(bytes);
            os.flush();
            os.close();
        } catch (Exception e) {
            log.error("general return stream img error! req: {}, e:{}", request, e);
            if (StringUtils.isNotBlank(e.getMessage())) {
                throw new InternalError(e.getMessage());
            } else {
                throw new InternalError(null);
            }
        }
    }
}
*/
