import { Component, OnInit } from '@angular/core';
import { RecipeService } from '../../services/recipe.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RecipeCardComponent } from '../../components/recipe-card/recipe-card.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RecipeCardComponent,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule
  ],
  styleUrls: ['./recipes.component.scss']
})
export class RecipesComponent implements OnInit {
  recipes: any[] = [];
  searchTerm: string = '';

  constructor(private recipeService: RecipeService,  private router: Router) {}

  ngOnInit(): void {
    this.getAllRecipes();
  }

  navigateToCreateRecipe(): void {
    this.router.navigate(['/create-recipe']);
  }

  getAllRecipes(): void {
    this.recipeService.getAllRecipes().subscribe((data) => {
      this.recipes = data;
    });
  }

  searchRecipes(): void {
    this.recipeService.searchRecipes(this.searchTerm).subscribe((data) => {
      this.recipes = data;
    });
  }

  searchByMealType(mealType: string): void {
    this.recipeService.searchRecipes('',[mealType]).subscribe((data) => {
      this.recipes = data;
    });
  }

  deleteRecipe(id: number): void {
    this.recipeService.deleteRecipe(id).subscribe(() => {
      this.recipes = this.recipes.filter((recipe) => recipe.id !== id);
    });
  }
}
