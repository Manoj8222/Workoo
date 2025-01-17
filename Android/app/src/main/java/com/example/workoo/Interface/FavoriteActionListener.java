package com.example.workoo.Interface;

public interface FavoriteActionListener {
    void onAddToFavorite(Long taskerId, int position);
    void onRemoveFromFavorite(Long taskerId, int position);
}
