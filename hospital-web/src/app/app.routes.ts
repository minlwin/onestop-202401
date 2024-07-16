import { Routes } from '@angular/router';
import { SigninComponent } from './pages/home/signin/signin.component';
import { SignupComponent } from './pages/home/signup/signup.component';
import { TopPageComponent } from './pages/home/top-page/top-page.component';
import { ChangePassComponent } from './pages/home/change-pass/change-pass.component';

export const routes: Routes = [
  {path: "home", children: [
    {path: "top", component: TopPageComponent, data: {showCover: true}},
    {path: "signin", component: SigninComponent, title: 'Sign In'},
    {path: "signup", component: SignupComponent, title: 'Sign Up'},
    {path: "change-pass", component: ChangePassComponent, title: 'Change Password'},
    {path: "", redirectTo: "/home/top", pathMatch: 'full'}
  ]},
  {
    path: "member",
    loadChildren: () => import('./member.routes').then(m => m.routes)
  },
  {path: "", redirectTo: "/home", pathMatch: 'full'}
];
