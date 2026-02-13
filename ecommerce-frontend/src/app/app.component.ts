import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AuthService } from './core/services/auth.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  standalone:true,
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'ecommerce-frontend';
  constructor(private authService:AuthService){}

  ngOnInit():void{
    this.authService.currentUser$.subscribe();
  }
}
