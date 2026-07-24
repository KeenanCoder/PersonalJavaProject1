document.getElementById('startBtn').addEventListener('click', () => {
  const playerX = document.getElementById('playerX').value.trim() || 'Player 1';
  const playerO = document.getElementById('playerO').value.trim() || 'Player 2';

  const params = new URLSearchParams({ mode: 'two-player', playerX, playerO });
  window.location.href = `game.html?${params.toString()}`;
});