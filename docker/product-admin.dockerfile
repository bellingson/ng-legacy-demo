
FROM nginx

RUN rm -rf /usr/share/nginx/html
COPY dist /usr/share/nginx/html

RUN rm -rf /etc/nginx/conf.d/default.conf
COPY product-admin.conf /etc/nginx/conf.d
