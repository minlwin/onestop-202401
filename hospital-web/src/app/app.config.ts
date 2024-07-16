import { ApplicationConfig, ErrorHandler, provideZoneChangeDetection } from '@angular/core';
import { provideRouter, TitleStrategy, withComponentInputBinding } from '@angular/router';

import { routes } from './app.routes';
import { AppTitleStrategy } from './services/utils/app-title.strategy';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { accessTokenInterceptor } from './services/utils/access-token.interceptor';
import { tokenRefreshInterceptor } from './services/utils/token-refresh.interceptor';
import { AppExceptionHandler } from './services/utils/app-exception-handler';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes, withComponentInputBinding()),
    {provide: TitleStrategy, useClass: AppTitleStrategy},
    provideHttpClient(
      withInterceptors([
        accessTokenInterceptor,
        tokenRefreshInterceptor
      ])
    ),
    {provide: ErrorHandler, useClass: AppExceptionHandler}
  ]
};
