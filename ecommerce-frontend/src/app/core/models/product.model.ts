export interface Product {
  id: number;
  name: string;
  description: string;
  price: number;
  stockQuantity: number;
  imageUrl: string;
  categoryId: number;
  categoryName: string;
  active: boolean;
}

export interface Category {
  id: number;
  name: string;
  description: string;
}

export interface CartItem {
  product: Product;
  quantity: number;
}