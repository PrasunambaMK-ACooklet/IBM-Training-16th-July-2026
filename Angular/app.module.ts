import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule, AppRoutingModule],
  bootstrap: [AppComponent],
})
export class AppModule {}


// import { HttpClientModule } from '@angular/common/http';
//
// @NgModule({
//   imports: [HttpClientModule],
// })
// export class AppModule {}
