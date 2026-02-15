import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../../../core/services/auth.service';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';


@Component({
  selector: 'app-login',
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './login.component.html',
  standalone: true,
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginForm!:FormGroup;
  isLoading=false;
  showPassword=false;

  constructor(private fb:FormBuilder,private authService:AuthService,private router:Router,private toastr:ToastrService){
    this.loginForm=this.fb.group({
      email:['',[Validators.required,Validators.email]],
      password:['',[Validators.required,Validators.minLength(6)]],
      rememberMe:[false]
    });
    if(this.authService.isLoggedIn()){
      this.router.navigate(['/products']);
    }
  }

  onSubmit():void{
    if(this.loginForm.invalid){
      this.markFormGroupTouched(this.loginForm);
      return ;
    }

    this.isLoading=true;
    const loginRequest={
      email:this.loginForm.get('email')?.value,
      password:this.loginForm.get('password')?.value
    };

    this.authService.login(loginRequest).subscribe({
      next:(response)=>{
        this.toastr.success('Connnexion réussie!','Bienvenue!');
        this.router.navigate(['/products']);
      },
      error:(error)=>{
        this.isLoading=false;
        const errorMessage=error.response?.data?.message||'Erreur de connexion';
        this.toastr.error(errorMessage,'Erreur');
      },
      complete:()=>{
        this.isLoading=false;
      }
    });
  }

  togglePasswordVisibility():void{
    this.showPassword=!this.showPassword;
  }

  private markFormGroupTouched(formGroup:FormGroup):void{
    Object.values(formGroup.controls).forEach(control=>{
      control.markAsTouched();
      if(control instanceof FormGroup){
        this.markFormGroupTouched(control);
      }
    })
  }

  get email(){
    return this.loginForm.get('email');
  }

  get password(){
    return this.loginForm.get('password');
  }


}
