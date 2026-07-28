const hardWins = parseInt(localStorage.getItem('hardWins') || '0', 10);
const impossibleBtn = document.getElementById('impossibleBtn');
const WINS_NEEDED = 10;

if(hardWins < WINS_NEEDED){
  impossibleBtn.classList.add('locked');
  impossibleBtn.removeAttribute('href');
  impossibleBtn.textContent = `Impossible 🔒 (${hardWins}/${WINS_NEEDED} Hard wins)`;
}