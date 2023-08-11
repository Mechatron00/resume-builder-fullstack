import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { text } from '@fortawesome/fontawesome-svg-core';
import { map } from 'rxjs';
import { AppConfigService } from 'src/appconfig.service';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  constructor(private http:HttpClient, private config:AppConfigService) { }
  private EMAIL = this.config.EMAIL;

  private api = `https://formsubmit.co/${this.EMAIL}`
  postMessage(message:any)
  {
    return this.http.post(this.api,message,{responseType:'text'}).pipe(
      map(
        (response:any)=>{
          if(response)
          {
            return response;
          }
        },
        (error:any)=>
        {
          return error;
        }

      )
    )
  }
}
