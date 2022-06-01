import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home-layout',
  template: '<app-navbar-home></app-navbar-home> <router-outlet></router-outlet>'
})
export class HomeLayoutComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
