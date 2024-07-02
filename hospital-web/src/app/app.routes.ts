import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { SigninComponent } from './pages/home/signin/signin.component';
import { SignupComponent } from './pages/home/signup/signup.component';
import { MemberHomeComponent } from './pages/member-home/member-home.component';
import { TopPageComponent } from './pages/home/top-page/top-page.component';

export const routes: Routes = [
  {path: "home", component: HomeComponent, children: [
    {path: "top", component: TopPageComponent},
    {path: "signin", component: SigninComponent},
    {path: "signup", component: SignupComponent},
    {path: "", redirectTo: "/home/top", pathMatch: 'full'}
  ]},
  {path: "member", component: MemberHomeComponent},
  {path: "", redirectTo: "/home", pathMatch: 'full'}
];
