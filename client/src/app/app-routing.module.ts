import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HomeLayoutComponent } from './layouts/home-layout/home-layout.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { AppLayoutComponent } from './layouts/app-layout/app-layout.component';
import { ProfileComponent } from './components/profile/profile.component';
import { SearchComponent } from './components/search/search.component';
import { HomeComponent } from './components/home/home.component';
import { MessageComponent } from './components/search/people-card/message/message.component';

const routes: Routes = [
  {
    path: "",
    component: HomeLayoutComponent,
    children:[
      {
        path: 'login', component: LoginComponent 
      },
      { 
        path: 'registration', component: RegistrationComponent 
      },
      {
        path: 'home', component: HomeComponent
      }
    ]
  },
  {
     path:"",
     component: AppLayoutComponent,
     children: [
       {
         path: "profile",
         component: ProfileComponent
       },
       {
         path: "search",
         component: SearchComponent
       },
       {
         path: "message",
         component: MessageComponent
       }
     ]
  }
  
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports:[
    RouterModule
  ]
})
export class AppRoutingModule { }
