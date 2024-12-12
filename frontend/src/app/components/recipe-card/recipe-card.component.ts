import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-recipe-card',
  templateUrl: './recipe-card.component.html',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule
  ],
  styleUrls: ['./recipe-card.component.scss']
})
export class RecipeCardComponent {
  @Input() recipe: any;
  @Output() onDelete = new EventEmitter<number>();
  showAllIngredients = false;

  getMealTypeColor(mealType: string | null | undefined): string {
    if (!mealType) return 'gray';
    
    switch (mealType.toUpperCase()) {
      case 'BREAKFAST': return 'yellow';
      case 'LUNCH': return 'green';
      case 'DINNER': return 'orange';
      default: return 'gray';
    }
  }

  deleteRecipe(): void {
    this.onDelete.emit(this.recipe.id);
  }
}
