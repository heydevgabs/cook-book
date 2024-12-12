import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RecipeService } from '../../services/recipe.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-recipe',
  templateUrl: './create-recipe.component.html',
  styleUrls: ['./create-recipe.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatIconModule
  ],
})
export class CreateRecipeComponent {
  recipeName: string = '';
  recipeDescription: string = '';
  recipeMealType: string = 'BREAKFAST'; // Default value
  recipeTimeRequired: number = 0;
  ingredients: { name: string; amount: string }[] = [];

  constructor(private recipeService: RecipeService, private router: Router) {}

  navigateToRecipes(): void {
    this.router.navigate(['/recipes']);
  }

  addIngredient(): void {
    this.ingredients.push({ name: '', amount: '' });
  }

  removeIngredient(index: number): void {
    this.ingredients.splice(index, 1);
  }

  createRecipe(): void {
    const recipe = {
      name: this.recipeName,
      description: this.recipeDescription,
      mealType: this.recipeMealType,
      timeRequired: this.recipeTimeRequired,
      ingredients: this.ingredients,
    };

    this.recipeService.createRecipe(recipe).subscribe(() => {
      alert('Recipe created successfully!');
      // Reset form after submission
      this.recipeName = '';
      this.recipeDescription = '';
      this.recipeMealType = 'BREAKFAST';
      this.recipeTimeRequired = 0;
      this.ingredients = [];
    });
  }
}
