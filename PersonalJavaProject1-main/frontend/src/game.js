const megaBoardEl = document.getElementById('megaBoard');
//FIXME: Allow the player to decide who goes first (if applicable)
//for both single player and two player modes
//assigns the first player to always be X
let currentPlayer = 'X';
//FIXME: either hardcode the initial player scores or assign it to a variable
let scoreX = 0;
let scoreO = 0;

//reads the names/difficulty modes from the URL
const params = new URLSearchParams(window.location.search);
//'two-player' or null
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


// builds the 9 mini-boards, each with 9 cells
//uses 2 loops
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

//updates the events going on around the board
function updateActiveBoard(activeRow, activeCol){
  document.querySelectorAll('.mini-board').forEach(board => {
    board.classList.remove('active');
    const r = Number(board.dataset.mainRow);
    const c = Number(board.dataset.mainCol);

    if(activeRow === -1){
      //free choice, highlights all the open boards to choose
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
        overlay.textContent = '—'; // or "TIE"
        board.appendChild(overlay);
      }
    }
  });
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

  // reset the visual board
  document.querySelectorAll('.cell').forEach(cell => {
    cell.textContent = '';
    cell.classList.remove('taken');
  });
  document.querySelectorAll('.mini-board').forEach(board => {
    board.classList.remove('won-X', 'won-O');
    const overlay = board.querySelector('.mini-board-overlay');
    if (overlay) overlay.remove();
  });

  currentPlayer = 'X';
  document.getElementById('status').textContent = '';
  document.getElementById('projectorOverlay').classList.remove('visible');

  updateActiveBoard(-1, -1);
});

//calls the backend
async function handleCellClick(e){
  const cell = e.target;

  if(cell.classList.contains('taken')) return;

  const{ mainRow, mainCol, miniRow, miniCol} = cell.dataset;

  //fetches using the localhost port number
  try{
    const response = await fetch('http://localhost:8080/api/move', {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify({
        mainRow: Number(mainRow),
        mainCol: Number(mainCol),
        miniRow: Number(miniRow),
        miniCol: Number(miniCol),
        player: currentPlayer
      })
    });

    const data = await response.json();

    if(data.success){
      cell.textContent = currentPlayer;
      cell.classList.add('taken');

      updateActiveBoard(data.activeRow, data.activeCol);
      updateMiniBoardWinners(data.miniBoardWinners);

      if (data.gameOver){
        const winner = data.winner.trim();
        if(winner === 'X'){
          scoreX++;
          document.getElementById('scoreX').textContent = scoreX;
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

      currentPlayer = currentPlayer === 'X' ? 'O' : 'X';
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

//FIXME: explain these lines of code for clarity
fetch('http://localhost:8080/api/new-game', {method: 'POST'}).then(() => {
  updateActiveBoard(-1, -1);
})
.catch(err => console.error('Failed to start new game:', err));