// ===== DEVELOPMENT ONLY (CHANGE WHEN TESTING AND DELETE WHEN TESTING IS FINISHED)=====
const RESET_PROGRESS = false;

if (RESET_PROGRESS) {
    localStorage.clear();
}
// ============================

const hardWins = parseInt(localStorage.getItem('wins_hard') || '0', 10);
const impossibleBtn = document.getElementById('impossibleBtn');
const WINS_NEEDED = 10;

if(hardWins < WINS_NEEDED){
  impossibleBtn.classList.add('locked');
  impossibleBtn.removeAttribute('href');
  impossibleBtn.textContent = '?';
}
else{
  impossibleBtn.textContent = 'Impossible';
}

function getStarInfo(wins){

  //Black Diamond
  if(wins >= 100000) return { stars: 1, tier: "black-diamond" };

  //Diamond
  if(wins >= 20000) return { stars: 3, tier: "diamond" };
  if(wins >= 10000) return { stars: 2, tier: "diamond" };
  if(wins >= 5000) return { stars: 1, tier: "diamond" };

  //Platnium
  if(wins >= 3000) return { stars: 3, tier: "platinum" };
  if(wins >= 2000) return { stars: 2, tier: "platinum" };
  if(wins >= 1000) return { stars: 1, tier: "platinum" };

  //Gold
  if(wins >= 500) return { stars: 3, tier: "gold" };
  if(wins >= 250) return { stars: 2, tier: "gold" };
  if(wins >= 100) return { stars: 1, tier: "gold"  };

  //Silver
  if(wins >= 80) return { stars: 3, tier: "silver"  };
  if(wins >= 60) return { stars: 2, tier: "silver"  };
  if(wins >= 40) return { stars: 1, tier: "silver"  };

  //Bronze
  if(wins >= 20) return { stars: 3, tier: "bronze"  };
  if(wins >= 15) return { stars: 2, tier: "bronze"  };
  if(wins >= 100) return { stars: 1, tier: "bronze"  };

  return { stars: 0, tier: "none" };
}

function renderStars(difficulty, containerId){
  const wins = parseInt(localStorage.getItem(`wins_${difficulty}`) || '0', 10);
  const { stars, tier } = getStarInfo(wins);
  const container = document.getElementById(containerId);

  if(!container) return;

  container.innerHTML = '';

  for(let i = 0; i < stars; i++){
    const star = document.createElement('span');
    star.className = `star ${tier}`;
    star.textContent = '★';
    container.appendChild(star);
  }
}

renderStars('easy', 'starsEasy');
renderStars('medium', 'starsMedium');
renderStars('hard', 'starsHard');
renderStars('impossible', 'starsImpossible');