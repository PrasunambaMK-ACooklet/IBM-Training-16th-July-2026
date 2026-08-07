import {NgModule} from "@angular/core";
import { LoginComponent } from './login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RegisterComponent } from './register.component';

@NgModule({
  declarations: [LoginComponent, RegisterComponent],
  imports: [ReactiveFormsModule],
})
export class AuthModule {}
