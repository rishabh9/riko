/*
 * MIT License
 *
 * Copyright (c) 2018 Rishabh Joshi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.rishabh9.riko.upstox.websockets.messages;

import com.github.rishabh9.riko.upstox.websockets.models.WrappedWebSocket;
import com.google.common.base.MoreObjects;
import okio.ByteString;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Objects;

/**
 * Web socket binary message.
 */
public class BinaryMessage extends WebSocketMessage {

    private ByteString message;

    public BinaryMessage(WrappedWebSocket sender, ByteString message) {
        super(sender);
        this.message = message;
    }

    /**
     * @return Returns the number of bytes in the message.
     */
    public int getSize() {
        return message.size();
    }

    public String getMessageAsString() {
        return message.string(Charset.forName("UTF-8"));
    }

    public byte[] getMessageAsByteArray() {
        return message.toByteArray();
    }

    public ByteBuffer getMessageAsByteBuffer() {
        return message.asByteBuffer();
    }

    /**
     * @param stream The OutputStream to write the message to.
     * @throws IOException When something goes writing to the stream.
     */
    public void writeMessageToOutputStream(OutputStream stream)
            throws IOException {
        message.write(stream);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryMessage that = (BinaryMessage) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("message", getSize() < 10000 ?
                        getMessageAsString() : "message-more-than-10kb-hidden")
                .add("sender", getSender())
                .toString();
    }
}
