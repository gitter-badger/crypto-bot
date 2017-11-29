package org.achfrag.crypto.bitfinex.commands;

import org.achfrag.crypto.bitfinex.BitfinexApiBroker;
import org.achfrag.crypto.bitfinex.BitfinexOrder;
import org.json.JSONObject;

public class OrderCommand extends AbstractAPICommand {

	private long cid;
	private BitfinexOrder bitfinexOrder;

	public OrderCommand(final long cid, final BitfinexOrder bitfinexOrder) {
		this.cid = cid;
		this.bitfinexOrder = bitfinexOrder;
	}

	@Override
	public String getCommand(final BitfinexApiBroker bitfinexApiBroker) throws CommandException {
		
		final JSONObject orderJson = new JSONObject();
		orderJson.put("cid", cid);
		orderJson.put("type", bitfinexOrder.getType().getBifinexString());
		orderJson.put("symbol", bitfinexOrder.getSymbol());
		orderJson.put("amount", Double.toString(bitfinexOrder.getAmount()));
		orderJson.put("price", Double.toString(bitfinexOrder.getPrice()));

		if(bitfinexOrder.getPriceTrailing() != -1) {
			orderJson.put("price_trailing", bitfinexOrder.getPriceTrailing());
		}
		
		if(bitfinexOrder.getPriceAuxLimit() != -1) {
			orderJson.put("price_aux_limit", bitfinexOrder.getPriceAuxLimit());
		}
		
		if(bitfinexOrder.isHidden()) {
			orderJson.put("hidden", 1);
		}
		
		if(bitfinexOrder.isPostOnly()) {
			orderJson.put("postonly", 1);
		}
		
		final StringBuilder sb = new StringBuilder();
		sb.append("[0,\"on\", null, ");
		sb.append(orderJson.toString());
		sb.append("]\n");
		
		System.out.println(sb);
		
		return sb.toString();
	}

}