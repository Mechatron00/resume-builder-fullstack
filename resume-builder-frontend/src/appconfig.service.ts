import { Injectable } from '@angular/core';
import { environment } from './environments/environment';

@Injectable({
    providedIn: 'root'
  })
  export class AppConfigService 
  {
    API_URL = environment.apiUrl;
    EMAIL = environment.email;
  }