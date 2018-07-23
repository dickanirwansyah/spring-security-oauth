import { Component, OnInit } from '@angular/core';
import { AppService, Foo} from '../app.service';

@Component({
  selector: 'app-foo',
  templateUrl: './foo.component.html',
  styleUrls: ['./foo.component.css']
})
export class FooComponent implements OnInit {

  title = 'Get Data Foo';
  public foo = new Foo(1, 'sample foo');
  private foosUrl = 'http://localhost:10001/spring-security-oauth-resource/foos/';

  constructor(private _service:AppService) { }

  getFoo(){
    this._service.getResource(this.foosUrl+this.foo.id)
      .subscribe(data => this.foo = data,
                error => this.foo.name = 'Error');
  }

  ngOnInit() {
  }

}
