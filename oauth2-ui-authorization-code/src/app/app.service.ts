import { Injectable } from '@angular/core';
import { Cookie } from 'ng2-cookies';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

export class Foo{
  constructor(
    public id: number,
    public name: string
  ){}
}

@Injectable()
export class AppService {

    public clientId = 'fooClientIdPassword';
    public redirectUri = 'http://localhost:4200/';

    constructor(private _httpClient: HttpClient) {}

    retrieveToken(code){
    let params = new URLSearchParams();
    params.append('grant_type','authorization_code');
    params.append('client_id', this.clientId);
    params.append('redirect_uri', this.redirectUri);
    params.append('code',code);

    let headers = new HttpHeaders({'Content-type': 'application/x-www-form-urlencoded; charset=utf-8', 'Authorization': 'Basic '+btoa(this.clientId+":secret")});
     this._httpClient.post('http://localhost:10000/spring-security-oauth-server/oauth/token', params.toString(), { headers: headers })
    .subscribe(
      data => this.saveToken(data),
      err => alert('Invalid Credentials')
    );
  }

    saveToken(token) {
      var expirateDate = new Date().getTime() + (1000 * token.expires_in);
      Cookie.set("access_token", token.access_token, expirateDate);
      window.location.href='http://localhost:4200';
      console.log("Obtained Access Token");
    }

    getResource(resourceUrl): Observable<any> {
        var headers = new HttpHeaders({'Content-type': 'application/x-www-form-urlencoded; charset=utf-8', 'Authorization': 'Bearer '+Cookie.get('access_token')});
        return this._httpClient.get(resourceUrl, { headers: headers })
        .catch((error:any)=>Observable.throw(error.json().error || 'Server Error.'));
    }

    checkCredential() {
        return Cookie.check('access_token');
    }

    logout() {
        Cookie.delete('access_token');
        window.location.reload();
    }
}
