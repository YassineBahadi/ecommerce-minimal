import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';
import { adminGuard } from './core/guards/admin.guard';

export const routes: Routes = [
    {
        path:'',
        redirectTo:'products',
        pathMatch:'full'
    },
    {
        path:'auth',
        loadChildren:()=>
            import('./features/auth/auth.module')
                .then(m=>m.AUTH_ROUTES)
    },
//     {
//         path:'products',
//         loadChildren:()=>
//             import('./features/products/products.routes')
//                 .then(m=>m.PRODUCTS_ROUTES),

//     },
//     {
//         path:'cart',
//         loadChildren:()=>
//             import('./features/cart/cart.routes')
//                 .then(m=>m.CART_ROUTES),
//         canActivate:[authGuard]
//     },
//     {
//         path:'checkout',
//         loadChildren:()=>
//             import('./features/checkout/checkout.routes')
//                 .then(m=>m.CHECKOUT_ROUTES),
//         canActivate:[authGuard]
//     },

//      {
//     path: 'admin',
//     loadChildren: () =>
//       import('./features/admin/admin.routes')
//         .then(m => m.ADMIN_ROUTES),
//     canActivate: [adminGuard]
//   },

  {
    path:'**',
    redirectTo:'products'
  }

];
