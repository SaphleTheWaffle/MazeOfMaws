var exports = module.exports = {};

exports.initialisePlayer = function(user) {
	var player = 
		{
		};

	player.id = user.id;
	player.name = user.username;
	player.gameState = 0;
	player.x = -1;
	player.y = -1;
		
	return player;
}

exports.startGame = function(player) {
	if (player.gameState == 0) {
		player.gameState = 1;
	}
}

exports.endGame = function(player) {
	player.gameState = 0;
}