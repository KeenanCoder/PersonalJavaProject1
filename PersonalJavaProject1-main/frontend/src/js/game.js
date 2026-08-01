const megaBoardEl = document.getElementById('megaBoard');
let currentPlayer = 'X';
let scoreX = 0;
let scoreO = 0;

const params = new URLSearchParams(window.location.search);
const mode = params.get('mode');
const difficulty = params.get('difficulty');

const nameX = mode === 'two-player' ? (params.get('playerX') || 'Player 1') : 'You';
const nameO = mode === 'two-player' ? (params.get('playerO') || 'Player 2') : 
`CPU (${difficulty ? difficulty[0].toUpperCase() + difficulty.slice(1) : 'Easy'})`;

document.getElementById('scoreXLabel').textContent = nameX;
document.getElementById('scoreOLabel').textContent = nameO;

function displayName(player){
  return player === 'X' ? nameX : nameO;
}

for (let mainRow = 0; mainRow < 3; mainRow++) {
  for (let mainCol = 0; mainCol < 3; mainCol++) {
    const miniBoardEl = document.createElement('div');
    miniBoardEl.className = 'mini-board';
    miniBoardEl.dataset.mainRow = mainRow;
    miniBoardEl.dataset.mainCol = mainCol;

    for (let miniRow = 0; miniRow < 3; miniRow++) {
      for (let miniCol = 0; miniCol < 3; miniCol++) {
        const cellEl = document.createElement('div');
        cellEl.className = 'cell';
        cellEl.dataset.mainRow = mainRow;
        cellEl.dataset.mainCol = mainCol;
        cellEl.dataset.miniRow = miniRow;
        cellEl.dataset.miniCol = miniCol;

        cellEl.addEventListener('click', handleCellClick);
        miniBoardEl.appendChild(cellEl);
      }
    }

    megaBoardEl.appendChild(miniBoardEl);
  }
}

function updateActiveBoard(activeRow, activeCol){
  document.querySelectorAll('.mini-board').forEach(board => {
    board.classList.remove('active');
    const r = Number(board.dataset.mainRow);
    const c = Number(board.dataset.mainCol);

    if(activeRow === -1){
      board.classList.add('active');
    }
    else if(r === activeRow && c === activeCol){
      board.classList.add('active');
    }
  });
}

function updateMiniBoardWinners(miniBoardWinners){
  document.querySelectorAll('.mini-board').forEach(board => {
    const r = Number(board.dataset.mainRow);
    const c = Number(board.dataset.mainCol);
    const status = miniBoardWinners[r][c];

    board.classList.remove('won-X', 'won-O', 'tied');

    if(status === 'X' || status === 'O'){
      board.classList.add(`won-${status}`);
      if(!board.querySelector('.mini-board-overlay')){
        const overlay = document.createElement('div');
        overlay.className = 'mini-board-overlay';
        overlay.textContent = status;
        board.appendChild(overlay);
      }
    }
    else if(status === 'T'){
      board.classList.add('tied');
      if(!board.querySelector('.mini-board-overlay')){
        const overlay = document.createElement('div');
        overlay.className = 'mini-board-overlay tied-overlay';
        overlay.textContent = '—';
        board.appendChild(overlay);
      }
    }
  });
}

// NEW: finds the exact cell the CPU played and marks it
function renderCpuMove(cpuMove, cpuPlayer){
  if(!cpuMove) return;
  const [mainRow, mainCol, miniRow, miniCol] = cpuMove;

  const cell = document.querySelector(
    `.cell[data-main-row="${mainRow}"][data-main-col="${mainCol}"][data-mini-row="${miniRow}"][data-mini-col="${miniCol}"]`
  );

  if(cell){
    cell.textContent = cpuPlayer;
    cell.classList.add('taken');
  }
}

function showProjector(winner) {
  document.getElementById('projectorMessage').textContent =
    winner ? `${displayName(winner)} Wins!` : "It's a Draw!";
  document.getElementById('projectorNameX').textContent = nameX;
  document.getElementById('projectorNameO').textContent = nameO;
  document.getElementById('projectorScoreX').textContent = scoreX;
  document.getElementById('projectorScoreO').textContent = scoreO;

  document.getElementById('projectorOverlay').classList.add('visible');
}

document.getElementById('restartBtn').addEventListener('click', async () => {
  await fetch('http://localhost:8080/api/new-game', { method: 'POST' });

  document.querySelectorAll('.cell').forEach(cell => {
    cell.textContent = '';
    cell.classList.remove('taken');
  });
  document.querySelectorAll('.mini-board').forEach(board => {
    board.classList.remove('won-X', 'won-O', 'tied');
    const overlay = board.querySelector('.mini-board-overlay');
    if (overlay) overlay.remove();
  });

  currentPlayer = 'X';
  document.getElementById('status').textContent = '';
  document.getElementById('projectorOverlay').classList.remove('visible');

  updateActiveBoard(-1, -1);
});

async function handleCellClick(e){
  const cell = e.target;

  if(cell.classList.contains('taken')) return;

  const{ mainRow, mainCol, miniRow, miniCol} = cell.dataset;

  try{
    const response = await fetch('http://localhost:8080/api/move', {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({
        mainRow: Number(mainRow),
        mainCol: Number(mainCol),
        miniRow: Number(miniRow),
        miniCol: Number(miniCol),
        player: currentPlayer,
        difficulty: mode === 'two-player' ? null : difficulty // NEW
      })
    });

    const data = await response.json();

    if(data.success){
      cell.textContent = currentPlayer;
      cell.classList.add('taken');

      // NEW: render the CPU's move if the backend made one
      if(data.cpuMove){
        const cpuPlayer = currentPlayer === 'X' ? 'O' : 'X';
        renderCpuMove(data.cpuMove, cpuPlayer);
      }

      updateActiveBoard(data.activeRow, data.activeCol);
      updateMiniBoardWinners(data.miniBoardWinners);

      if (data.gameOver){
        const winner = data.winner.trim();
        if(winner === 'X'){
          scoreX++;
          document.getElementById('scoreX').textContent = scoreX;

          if(mode !== 'two-player' && difficulty){
            const key = `wins_${difficulty}`;
            const wins = parseInt(localStorage.getItem(key) || '0', 10) + 1;
            localStorage.setItem(key, wins);
          }
        }
        else if (winner === 'O'){
          scoreO++;
          document.getElementById('scoreO').textContent = scoreO;
        }
       
        showProjector(winner);
      }
      else{
        document.getElementById('status').textContent = '';
      }

      if(!data.cpuMove){
        currentPlayer = currentPlayer === 'X' ? 'O' : 'X';
}
    }
    else {
      document.getElementById('status').textContent = 'Invalid move - try again!';
    }
  }
  catch (err){
    console.error('Failed to reach backend:', err);
    document.getElementById('status').textContent = 'Could not connect to server';
  }
}

fetch('http://localhost:8080/api/new-game', {method: 'POST'}).then(() => {
  updateActiveBoard(-1, -1);
})
.catch(err => console.error('Failed to start new game:', err));