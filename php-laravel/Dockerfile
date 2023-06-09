# Estágio de compilação
FROM composer:2.2 AS build

WORKDIR /app

COPY . .
RUN php artisan package:discover

COPY composer.* ./
RUN composer install --no-dev --optimize-autoloader --prefer-dist

RUN php artisan optimize:clear

FROM php:8.2.7-fpm

RUN apt-get update && apt-get install -y \
    nginx \
    supervisor

COPY --from=build /app /var/www/html

COPY docker/nginx.conf /etc/nginx/sites-available/default

RUN cp /usr/local/etc/php-fpm.d/www.conf.default /usr/local/etc/php-fpm.d/www.conf
RUN sed -i 's/listen = \/run\/php\/php8.2.7-fpm.sock/listen = 9000/' /usr/local/etc/php-fpm.d/www.conf

COPY docker/supervisord.conf /etc/supervisor/conf.d/supervisord.conf

RUN chown -R www-data:www-data /var/www/html/storage /var/www/html/bootstrap/cache

EXPOSE 80

CMD ["/usr/bin/supervisord", "-n", "-c", "/etc/supervisor/conf.d/supervisord.conf"]
