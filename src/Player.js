var exports = module.exports = {};

exports.initialisePlayer = function(userid) {
	var player = 
		{
		};

	player.id = userid;
	player.activeGame = false;
	player.x = -1;
	player.y = -1;
		
	return player;
}
